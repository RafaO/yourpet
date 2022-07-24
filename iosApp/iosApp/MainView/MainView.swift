import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: MainViewModel
    
    private let filter = Filter(genders: [.Female(), .Male()])
    
    var body: some View {
        HomeView(viewModel: HomeViewModel())
            .environment(\.colorScheme, viewModel.state.colorScheme)
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
