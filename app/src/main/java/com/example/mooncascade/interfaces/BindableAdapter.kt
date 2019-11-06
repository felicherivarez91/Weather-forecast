package com.example.mooncascade.interfaces

/**
 * @author Dmitry Tkachuk
 * Created on 06.11.2019
 * All rights reserved
 */
interface BindableAdapter<T> {
    fun setData(items: T)
}