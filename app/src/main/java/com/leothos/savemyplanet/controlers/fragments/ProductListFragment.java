package com.leothos.savemyplanet.controlers.fragments;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.ViewModel.ProductViewModel;
import com.leothos.savemyplanet.adapters.MyProductAdapter;
import com.leothos.savemyplanet.entities.MyProduct;
import com.leothos.savemyplanet.injections.Injection;
import com.leothos.savemyplanet.injections.ViewModelFactory;
import com.leothos.savemyplanet.utils.BusinessLogic;
import com.leothos.savemyplanet.utils.ItemClickSupport;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListFragment extends Fragment implements View.OnClickListener, MyProductAdapter.Listener {

    public static final String CUSTOM_DIALOG_IMAGE = "CUSTOM_DIALOG_IMAGE";
    public static final String BUNDLE_KEY_RESPONSE = "BUNDLE_KEY";
    private static final String TAG = "ProductListFragment";
    private static final long COUNTDOWN_RUNNING_TIME = 500;
    // Constant
    private static Integer INDICATOR_MIN = -1;
    private static Integer INDICATOR_MAX = 3;
    // Widget
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.message_empty)
    TextView mEmptyMessage;
    @BindView(R.id.list_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    CardView mCardView;
    @BindView(R.id.edit_text_product_name)
    EditText mEditProductName;
    @BindView(R.id.edit_text_product_brand)
    EditText mEditBrand;
    @BindView(R.id.validate_search)
    ImageButton mValidatorSearch;
    @BindView(R.id.button_with)
    RadioButton mRadioWith;
    @BindView(R.id.button_without)
    RadioButton mRadioWithout;
    @BindView(R.id.frag_swipe_layout)
    SwipeRefreshLayout mRefreshLayout;
    private MenuItem exit;
    private MenuItem search;
    // Var
    private ProductViewModel mProductViewModel;
    private MyProductAdapter mAdapter;
    // Anim
    private Animation animationUp;
    private Animation animationDown;


    public ProductListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        this.init();
        return view;
    }

    private void init() {
        this.configureToolBar();
        this.configureRecyclerView();
        this.configureViewModel();
        this.getProductList();
        this.configureSwipeRefreshLayout();
        this.mValidatorSearch.setOnClickListener(v -> this.clickOnValidatorButton());
        this.mRadioWith.setOnClickListener(this);
        this.mRadioWithout.setOnClickListener(this);
        this.initAnimation();

    }

    // -------------
    // Configuration
    // -------------

    private void configureViewModel() {
        ViewModelFactory modelFactory = Injection.provideViewModelFactory(getContext());
        this.mProductViewModel = ViewModelProviders.of(this, modelFactory).get(ProductViewModel.class);
    }

    private void configureRecyclerView() {
        this.mAdapter = new MyProductAdapter(Glide.with(this), this);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerViewItemClickHandler();

    }

    private void configureToolBar() {
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle(getString(R.string.title_history));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                this.entryAnimation();
                this.closeKeyboard();
                break;
            case R.id.menu_exit:
                this.exitAnimation();
                this.closeKeyboard();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        exit = menu.findItem(R.id.menu_exit);
        search = menu.findItem(R.id.menu_search);

        search.setOnMenuItemClickListener(item -> {
            exit.setVisible(true);
            return false;
        });
        exit.setOnMenuItemClickListener(item -> {
            exit.setVisible(false);
            return false;
        });

    }

    // -------------
    // Action
    // -------------

    private void recyclerViewItemClickHandler() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_products_item)
                .setOnItemClickListener((recyclerView, position, v) -> {

                    this.collapseDetailProduct(v, position);
                });
    }

    @Override
    public void onPictureClicked(int position) {
        Log.d(TAG, "onPictureClicked: ok " + position);
        this.openCustomImageResizer(mAdapter.getSingleProduct(position).getUrlPicture());
    }

    // When the search dialog layout is visible
    private void clickOnValidatorButton() {
        //launch request
        this.handleSearchQuery();
        //hide the views
        this.exitAnimation();
        this.closeKeyboard();
        this.exit.setVisible(false);
    }

    private void handleSearchQuery() {
        String productName = !mEditProductName.toString().equals("") ?
                "%" + mEditProductName.getText().toString() + "%" : "%";
        String category = !mEditBrand.toString().equals("") ?
                "%" + mEditBrand.getText().toString() + "%" : "%";

        // Search request
        Log.d("Tag", "handleSearchQuery: " + INDICATOR_MIN + " - " + INDICATOR_MAX);
        this.mProductViewModel.searchProducts(productName, category, INDICATOR_MIN, INDICATOR_MAX)
                .observe(this, this::updateProductList);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_with:
                INDICATOR_MIN = 1;
                INDICATOR_MAX = 3;
                break;
            case R.id.button_without:
                INDICATOR_MIN = -1;
                INDICATOR_MAX = 0;
                break;
        }
        closeKeyboard();
    }

    private void closeKeyboard() {
        View view = Objects.requireNonNull(getActivity()).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    // -------------
    // UI
    // -------------

    private void getProductList() {
        this.mProductViewModel.getAllProducts().observe(this, this::updateProductList);
    }

    private void updateProductList(List<MyProduct> myProducts) {
        this.mEmptyMessage.setVisibility(myProducts.size() == 0 ? View.VISIBLE : View.GONE);
        this.mAdapter.updateData(myProducts);
        mRefreshLayout.setRefreshing(false);
    }

    private void resetSearchWidgetValues() {
        mEditProductName.setText("");
        mEditBrand.setText("");
        mRadioWith.setChecked(false);
        mRadioWithout.setChecked(false);
        INDICATOR_MIN = -1;
        INDICATOR_MAX = 3;
    }

    private void configureSwipeRefreshLayout() {
        this.mRefreshLayout.setOnRefreshListener(this::getProductList);
    }

    private void openCustomImageResizer(String url) {
        DisplayImageBigSize imageBigSize = new DisplayImageBigSize();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_RESPONSE, url);
        imageBigSize.setArguments(args);
        imageBigSize.setStyle(DialogFragment.STYLE_NO_TITLE, DialogFragment.STYLE_NO_INPUT);
        if (getFragmentManager() != null) {
            imageBigSize.show(getFragmentManager(), CUSTOM_DIALOG_IMAGE);
        }

    }

    //Animation to show the productd detail in recycler view
    private void collapseDetailProduct(View v, int position) {
        RelativeLayout container = v.findViewById(R.id.item_container_collapser);
        TextView ingredients = v.findViewById(R.id.collapsing_ingredients);
        ImageView nutriScore = v.findViewById(R.id.collapsing_nutri_score_image);
        ImageView arrow = v.findViewById(R.id.arrow_icon);
        //Set widgets
        ingredients.setText(mAdapter.getSingleProduct(position).getIngredients());
        nutriScore.setImageResource(BusinessLogic.getNutriScore(mAdapter.getSingleProduct(position).getScoreGrade(),
                R.drawable.nutriscore_a,
                R.drawable.nutriscore_b,
                R.drawable.nutriscore_c,
                R.drawable.nutriscore_d,
                R.drawable.nutriscore_e));
        //Handle animations
        if (container.isShown()) {
            container.startAnimation(animationUp);
            this.addCountDownTimer(container);
            arrow.setRotation(0);

        } else {
            container.setVisibility(View.VISIBLE);
            container.startAnimation(animationDown);
            arrow.setRotation(90);
        }
    }

    // -------------
    // Animation
    // -------------

    private void entryAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_X, mCardView.getWidth(), 0);
        animator.setDuration(700);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
    }

    private void exitAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_X, 0, mCardView.getWidth());
        animator.setDuration(500);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
        //To clear all values
        this.resetSearchWidgetValues();
    }

    private void initAnimation() {
        animationUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
    }

    //Necessary to avoid wrong animationUp behavior
    private void addCountDownTimer(View v) {
        CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
               v.setVisibility(View.GONE);
            }
        };
        countDownTimerStatic.start();
    }
}
