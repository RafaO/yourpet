import SwiftUI

struct PetsScreen: View {
    
    @ObservedObject var viewModel: PetsViewModel
    
    var body: some View {
        Group{
            switch viewModel.state {
                case .loading: Text("Loading..")

                case .content (let content): PetsListView(content: content)

                case .error(let message): PetsListErrorView(message: message) {
                    viewModel.viewCreated()

                }
            }
        }.onAppear {
            viewModel.viewCreated()
        }
    }
}
