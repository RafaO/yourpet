import SwiftUI
import shared

struct MainView: View {
    @ObservedObject var viewModel: MainViewModel
    
    var body: some View {
        HomeView(homeViewModel: HomeViewModel(), mainViewModel: viewModel)
            .environment(\.colorScheme, viewModel.state.colorScheme)
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
