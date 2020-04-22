package com.example.knight.dagger

object Dagger2ComponentFactory {
    @PublishedApi
    internal lateinit var topLevelComponentProvider: TopLevelComponentProvider

    private val appScopedComponents = mutableMapOf<Class<*>, Any>()

    @JvmStatic
    fun init(singleton: TopLevelComponentProvider) {
        topLevelComponentProvider = singleton
    }

    @JvmStatic
    fun clear(subcomponentClass: Class<*>) {
        appScopedComponents.remove(subcomponentClass)
    }

    fun <G : Graph, SC : Graph, SCB : SubcomponentBuilder<SC>> create(
        graphClass: Class<G>,
        subComponentBuilderSupplier: (G) -> SCB,
        buildSubComponent: (SCB) -> SCB = { it }
    ): SC = buildSubComponent.invoke(
        subComponentBuilderSupplier.invoke(
            topLevelComponentProvider.component(graphClass)
        )
    ).build()


}