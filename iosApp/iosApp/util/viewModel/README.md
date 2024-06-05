# Documentação da Classe `SharedViewModelStoreOwner`


## **Visão Geral**


> A classe SharedViewModelStoreOwner fornece uma maneira de compartilhar
> uma única instância de um ViewModel entre múltiplas Views ou
> componentes no seu aplicativo iOS. Isso é útil em cenários onde
> diferentes partes do seu aplicativo precisam acessar e modificar o
> mesmo estado e lógica.

```swift
import Foundation
  
class SharedViewModelStoreOwner<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {  
  var viewModelStore: ViewModelStore = ViewModelStore()  
  private let key: String = String(describing: type(of: VM.self))  
  init(_ viewModel: VM = .init()) {  
  viewModelStore.put(key: key, viewModel: viewModel)  
 }  var instance: VM {  
  get {  
  return viewModelStore.get(key: key) as! VM  
        }  
  }  
  deinit {  
  viewModelStore.clear()  
  }  
}
```



## **Declaração**
```swift  
class SharedViewModelStoreOwner<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {  
  // ...  
}
```



## **Parâmetros Genéricos**
- `VM`: O tipo do ViewModel que será armazenado e compartilhado.


## **Propriedades**
- `viewModelStore`: Uma instância de ViewModelStore que armazena o ViewModel.
- `instance`: Uma propriedade computada que retorna a instância do ViewModel armazenada no viewModelStore.


## **Inicializador**
```swift  
init(_ viewModel: VM = .init())
````

- `viewModel`: Uma instância opcional do ViewModel. Se nenhuma instância for fornecida, uma nova é criada usando o inicializador padrão do ViewModel.

## **Deinitializer**
```swift
  deinit {  
  viewModelStore.clear()  
  }
 ````
- Limpa o `viewModelStore` quando a instância do `SharedViewModelStoreOwner` é desalocada.

## **Conformidade a Protocolos**

- `ObservableObject`: Permite que a classe seja observada por Views SwiftUI para receber atualizações quando o estado do ViewModel mudar.
- `ViewModelStoreOwner`: Define a classe como proprietária de um `ViewModelStore`, permitindo que ela armazene e recupere ViewModels.



## **Cenários de Uso**

**Compartilhamento de Estado entre Telas**: Use um SharedViewModelStoreOwner para compartilhar um ViewModel entre múltiplas telas que precisam acessar e modificar os mesmos dados.
- **Componentes Reutilizáveis**: Use um SharedViewModelStoreOwner para fornecer acesso a um ViewModel compartilhado para componentes de UI que precisam interagir com o mesmo estado.



## **Exemplo de Uso**
```swift  
// Criando uma instância do SharedViewModelStoreOwner com um ViewModel  
// Em uma View pai (por exemplo, ContentView), crie e gerencie o SharedViewModelStoreOwner:
struct ContentView: View {
    @StateObject var sharedViewModelOwner = SharedViewModelStoreOwner(MyViewModel())

    var body: some View {
        // Passe a instância do ViewModel para as Views filhas:
        ChildView(viewModel: sharedViewModelOwner.instance)
    }
}

// Em uma View filha, acesse a instância compartilhada do ViewModel:
struct ChildView: View {
    @ObservedObject var viewModel: MyViewModel

    var body: some View {
        // Use o viewModel aqui
        Text("Valor do ViewModel: \(viewModel.someProperty)") 
    }
}
```
**Explicação:**


- `@StateObject` **na View Pai**: Na ContentView, declaramos uma propriedade @StateObject para criar e gerenciar o SharedViewModelStoreOwner. Isso garante que o ViewModel seja criado apenas uma vez e persista enquanto a ContentView estiver ativa.
- **Passando o `ViewModel` para `Views` Filhas**: A ContentView passa a instância do ViewModel (sharedViewModelOwner.instance) para a ChildView através do seu inicializador.
- **`@ObservedObject` na `View` Filha**: A ChildView usa @ObservedObject para observar as mudanças no ViewModel e atualizar sua interface quando necessário.


> Com essa abordagem, você garante o compartilhamento correto e eficiente do ViewModel entre múltiplas Views, aproveitando os recursos de gerenciamento de estado do SwiftUI.



## **Considerações**
- **Ciclo de Vida**: Gerencie o ciclo de vida do SharedViewModelStoreOwner de forma que ele persista enquanto as Views que precisam acessar o ViewModel estiverem ativas. Você pode, por exemplo, armazená-lo em um EnvironmentObject no SwiftUI.
- **Thread Safety**: Se o ViewModel for acessado de múltiplas threads, certifique-se de que ele seja thread-safe para evitar problemas de concorrência. Use mecanismos de sincronização apropriados, como DispatchQueue ou atores, se necessário.



**Observações**
> Essa classe oferece uma solução simples e eficaz para compartilhar
> instâncias de ViewModel no iOS, promovendo a reutilização de código e
> a consistência do estado da aplicação. Lembre-se de adaptar o uso da
> classe às necessidades específicas do seu projeto e considerar as
> melhores práticas de gerenciamento de estado e ciclo de vida para
> garantir um aplicativo robusto e eficiente.
