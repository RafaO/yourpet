import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: PetsViewModel
    @State var showMenu = false
    
    var body: some View {
        NavigationView {
            GeometryReader { geometry in
                ZStack(alignment: .leading, content: {
                    Group{
                        switch viewModel.state {
                        case .loading:
                            Text("Loading..")
                            
                        case .content (let content): PetsListView(content: content)
                            
                        case .error(let message): PetsListErrorView(message: message) { viewModel.viewCreated() }
                        }
                    }.onAppear {
                        viewModel.viewCreated()
                    }
                    .offset(x: self.showMenu ? geometry.size.width/2 : 0)
                    .disabled(self.showMenu)
                    if self.showMenu {
                        MenuView()
                            .frame(width: geometry.size.width/2)
                    }
                })
            }
            .navigationBarItems(leading: (
                Button(action: {
                    withAnimation {
                        self.showMenu.toggle()
                    }
                }) {
                    Image(systemName: "line.horizontal.3")
                        .imageScale(.large)
                }
            ))
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
