package com.rose.mobiletrack.navigation

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_DASHBOARD= "dashboard"
const val ROUT_CONTACT= "contact"
const val ROUT_SERVICE= "service"
const val ROUT_SPLASH= "splash"
const val ROUT_FORM= "form"
const val ROUT_INTENT= "form"
const val ROUT_FARE= "form"

//authentication
const val ROUT_REGISTER= "register"
const val ROUT_LOGIN = "Login"

//SERVICES

const val ROUT_ADD_SERVICES = "add_Services"
const val ROUT_SERVICES_LIST = "Services_list"
const val ROUT_EDIT_SERVICES = "edit_Services/Id}"

// ✅ Helper function for navigation
fun editservicesRoute(servicesId: Int) = "edit_Services/$servicesId"

