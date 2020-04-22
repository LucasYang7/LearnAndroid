package com.example.yangzhe.learn.mvvm.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class ObserableUser(var firstName: ObservableField<String> = ObservableField(),
                         var lastName: ObservableField<String> = ObservableField(),
                         var age: ObservableInt = ObservableInt())