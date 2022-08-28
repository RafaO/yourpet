import SwiftUI
import shared

struct MainView: View {
    @ObservedObject var viewModel: MainViewModel
    
    private let filter = Filter(genders: [.Female(), .Male()])
    
    var body: some View {
        HomeView(homeViewModel: HomeViewModel())
            .environment(\.colorScheme, viewModel.state.colorScheme)
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
