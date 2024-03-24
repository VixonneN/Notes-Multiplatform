//
//  ComponentHolder.swift
//  iosApp
//
//  Created by MakBook  on 1.3.2024.
//

import ComposeApp

class ComponentHolder {
    let lifecycle: LifecycleRegistry
    let component: RootRootComponent
    
    init(factory: (ComponentContext) -> RootRootComponent) {
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let component = factory(DefaultComponentContext(lifecycle: lifecycle))
        self.lifecycle = lifecycle
        self.component = component

        lifecycle.onCreate()
    }

    deinit {
        lifecycle.onDestroy()
    }
}
