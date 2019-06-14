package com.leothos.savemyplanet.utils;

public interface JsInterface {

    default String PETITION_JS() {
        return "javascript:(function() { " +
                "var header = document.getElementById('header');" +
                "var footer = document.getElementById('footer');" +
                "var activity = document.getElementById('activity');" +
                "var formjumper = document.getElementById('formjumper');" +
                "var cookieconsent = document.getElementById('cookieconsent');" +
                "formjumper.style.display = 'none';" +
                "cookieconsent.style.display = 'none';" +
                "header.style.display = 'none';" +
                "footer.style.display = 'none';" +
                "activity.style.display = 'none';" +
                "var objRef = document.body;" +
                "objRef.innerHTML = document.getElementById('page').innerHTML;" +
                "window.print();" +
                "})()";
    }

    default String DONATION_JS() {
        return "javascript:(function() { " +
                "var header = document.getElementById('header');" +
                "var footer = document.getElementById('footer');" +
                "var activity = document.getElementById('activity');" +
                "var formjumper = document.getElementById('formjumper');" +
                "var cookieconsent = document.getElementById('cookieconsent');" +
                "formjumper.style.display = 'none';" +
                "cookieconsent.style.display = 'none';" +
                "header.style.display = 'none';" +
                "footer.style.display = 'none';" +
                "activity.style.display = 'none';" +
                "var objRef = document.body;" +
                "objRef.innerHTML = document.getElementById('page').innerHTML;" +
                "window.print();" +
                "})()";
    }

    default String INFORMATION_JS() {
        return "javascript:(function() { " +
                "var info = document.getElementById('start');" +
                "var objRef = document.body;" +
                "objRef.innerHTML = 'Loading';" +
                "objRef.parentNode.replaceChild(info, objRef);" +
                "document.getElementsByClassName('spread-buttons sticky')[0].style.display = 'none';" +
                "})()";
    }

}
