import UIKit
import SwiftUI
import ComposeApp

@main
struct iosApp: App {
    
    init() {
        DiKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
    
}
    
//    var module = Koin_coreModule(_createdAtStart: true)
//    
//    func a() {
//        module.single(qualifier: nil, createdAtStart: true, definition: { a , b in
//            
//        })
//    }
    
    
    
    struct ContentView: View {
        
        @State
        private var componentHolder = ComponentHolder {
            RootHelper(componentContext: $0).rootComponent
        }
        
        @Environment(\.scenePhase)
        var scenePhase: ScenePhase
        
        var body: some View {
            ComposeView(componentHolder)
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                    case .active: LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle)
                    case .inactive: LifecycleRegistryExtKt.pause(self.componentHolder.lifecycle)
                    case .background: LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle)
                    @unknown default: break
                    }
                }
                .onAppear {
                    LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle)
                }
                .onDisappear {
                    LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle)
                }
                .ignoresSafeArea(.all)
        }
    }
    
    struct ComposeView: UIViewControllerRepresentable {
        
        private var component: RootRootComponent
        
        init(_ componentHolder: ComponentHolder) {
            self.component = componentHolder.component
        }
        
        func makeUIViewController(context: Context) -> UIViewController {
            MainKt.MainViewController(rootComponent: component)
        }
        
        func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        }
    }
