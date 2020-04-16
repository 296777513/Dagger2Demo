package com.example.knight.dagger2

class CrossGuardRealChain(
    private val index: Int = 0,
    private val data: CrossGuardProcessor.ProcessorData,
    private val processors: List<CrossGuardProcessor> = ArrayList()
) : CrossGuardProcessor.Chain {

    override fun process(): Boolean {
        processors.takeIf {
            index < it.size
        }?.get(index)?.let { processor ->
            return processor.handle(
                CrossGuardRealChain(
                    index = index + 1,
                    data = data,
                    processors = processors
                )
            )
        } ?: return true
    }

    override fun request() = data

}