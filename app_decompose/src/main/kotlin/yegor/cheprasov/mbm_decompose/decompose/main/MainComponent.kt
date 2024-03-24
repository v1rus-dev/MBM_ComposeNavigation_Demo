package yegor.cheprasov.mbm_decompose.decompose.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import yegor.cheprasov.mbm_decompose.decompose.PageComponent

interface MainComponent {

    val pages: Value<ChildStack<*, PageComponent>>

    fun onAllClicked()

    fun onFavoriteClicked()

}