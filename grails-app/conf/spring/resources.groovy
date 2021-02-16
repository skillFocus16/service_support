package spring

import customer_service_support.CustomUserDetailsService
import customer_service_support.UserPasswordEncoderListener
beans = {
//    userPasswordEncoderListener(UserPasswordEncoderListener)
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
