package com.rose.mobiletrack.navigation

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_DASHBOARD= "dashboard"
const val ROUT_CONTACT= "contact"
const val ROUT_SPLASH= "splash"
const val ROUT_HISTORY= "history"
const val ROUT_PAYMENT= "payment"
const val ROUT_PRIVACY_POLICY= "privacy&policy"
const val ROUT_SETTING= "settings"
const val ROUT_RIDER_DETAILS= "rider details"
const val ROUT_RIDER_CONFIRMATION= "rider confirmation"
const val ROUT_TERMS_CONDITIONS= "terms and conditions"
const val ROUT_PROFILE= "profile"
const val ROUT_SUPPORT= "support"
const val ROUT_BOOKING= "booking"
const val ROUT_EDIT_PROFILE = "edit_profile"

//booking
const val ROUT_UPLOAD_BOOKING = "upload_task"
const val ROUT_VIEW_BOOKING = "view_task"

//authentication
const val ROUT_REGISTER= "register"
const val ROUT_LOGIN = "Login"


// ✅ Helper function for navigation
fun editbookingRoute(bookingId: Int) = "edit_booking/$bookingId"

