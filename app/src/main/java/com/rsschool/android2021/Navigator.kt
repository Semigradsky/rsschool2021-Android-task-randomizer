package com.rsschool.android2021

interface Navigator {
    fun openFirstFragment()
    fun openSecondFragment(min: Int, max: Int)
    fun saveGeneratedNumber(number: Int)
}
