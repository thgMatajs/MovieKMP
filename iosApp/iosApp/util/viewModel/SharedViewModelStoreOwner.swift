import Foundation


/// Classe que permite compartilhar uma única instância de um ViewModel entre múltiplas Views ou componentes no iOS.
class SharedViewModelStoreOwner<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {

    /// Armazena a instância do ViewModel.
    var viewModelStore: ViewModelStore = ViewModelStore()

    /// Chave única para identificar o ViewModel no ViewModelStore.
    private let key: String = String(describing: type(of: VM.self))

    /// Inicializa o SharedViewModelStoreOwner com uma instância opcional do ViewModel.
    /// - Parameter viewModel: Uma instância opcional do ViewModel. Se nenhuma instância for fornecida,
    /// uma nova é criada usando o inicializador padrão do ViewModel.
    init(_ viewModel: VM = .init()) {
        viewModelStore.put(key: key, viewModel: viewModel)
    }

    /// Propriedade computada que retorna a instância do ViewModel armazenada no ViewModelStore.
    var instance: VM {
        get {
            // Força o casting para o tipo VM, assumindo que o ViewModel correto foi armazenado.
            return viewModelStore.get(key: key) as! VM
        }
    }

    /// Limpa o ViewModelStore quando a instância do SharedViewModelStoreOwner é desalocada.
    deinit {
        viewModelStore.clear()
    }
}