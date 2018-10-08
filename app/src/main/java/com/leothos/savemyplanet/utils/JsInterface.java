package com.leothos.savemyplanet.utils;

public interface JsInterface {

    default String PETITION_JS() {
        return "javascript:(function() { " +
                "var petition = document.getElementById('petition-form');" +
                "var objRef = document.body;" +
                "objRef.innerHTML = 'replace body!';" +
                "objRef.parentNode.replaceChild(petition, objRef);" +
                "})()";
    }

    default String DONATION_JS() {
        return "javascript:(function() { " +
                "var donation = document.getElementById('form');" +
                "var objRef = document.body;" +
                "objRef.innerHTML = 'replace body!';" +
                "objRef.parentNode.replaceChild(donation, objRef);" +
                "})()";
    }

    default String INFORMATION_JS() {
        return "javascript:(function() { " +
                "var info = document.getElementById('start');" +
                "var objRef = document.body;" +
                "objRef.innerHTML = 'replace body!';" +
                "objRef.parentNode.replaceChild(info, objRef);" +
                "document.getElementsByClassName('spread-buttons sticky')[0].style.display = 'none';"+
                "})()";
    }

}
