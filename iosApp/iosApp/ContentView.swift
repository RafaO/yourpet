import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: PetsViewModel
    
    var body: some View {
        Group{
            switch viewModel.state {
            case .loading:
                Text("Loading..")
                
            case .content (let content):
                List(content.pets, id: \.name) { pet in
                    PetRow(pet: pet)
                }
            case .error(let message):
                Text(message)
            }
        }.onAppear {
            viewModel.viewCreated()
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
