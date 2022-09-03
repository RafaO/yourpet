import SwiftUI
import shared

struct PetsScreen: View {
    
    @ObservedObject var viewModel: PetsViewModel
    @ObservedObject var homeViewModel: HomeViewModel
    
    var body: some View {
        Group{
            switch viewModel.state {
                case .loading: Text("Loading..")

                case .content (let content): PetsListView(content: content)

                case .error(let message): PetsListErrorView(message: message) {
                    viewModel.viewCreated(genders: homeViewModel.genders)

                }
            }
        }.onAppear {
            viewModel.viewCreated(genders: homeViewModel.genders)
        }
    }
    
    func menuHidden() {
        viewModel.viewCreated(genders: homeViewModel.genders)
    }
}
