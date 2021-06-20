package com.kotl.jetpack.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * 当注入的类依赖系统组件Context时。使用预置Qualifier
 * 如@ActivityContext 或 @ApplicationContext
 * 对于Application和Activity，Hilt自动识别，无需添加任何注解声明，不包括子类
 *
 * 以下 如果@Singleton会报错，和@ActivityContext 不匹配。
 */
@ActivityScoped
class Driver @Inject constructor(@ActivityContext context: Context) {
    fun drive() {

    }
}