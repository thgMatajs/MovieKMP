import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModelStoreOwner = SharedViewModelStoreOwner<MovieListViewModel>()
    
    var body: some View {
        VStack {
            Observing(viewModelStoreOwner.instance.movie) { movie in
                
                if movie.isEmpty {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle())
                } else {
                    Text("Filme: \(movie)")
                    Divider()
                    Button("New Title") {
                        viewModelStoreOwner.instance.getMovies(page: Int32(Int.random(in: 1...10)))
                    }
                }
            }
            
//            Observing(viewModelStoreOwner.instance.movieResult) { state in
//                switch onEnum(of: state) {
//                case .loading:
//                    ProgressView().progressViewStyle(CircularProgressViewStyle())
//                case .error(let error):
//                    Text()
//                case .success(let success):
//                    Text("Filme: \(success.result)")
//                }
//            }
        }
    }
    
}

