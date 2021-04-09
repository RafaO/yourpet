import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: PetsViewModel
    
    @State var image: UIImage = UIImage()
    
    var body: some View {
        Group{
            switch viewModel.state {
            case .loading:
                Text("Loading..")
                
            case .content (let content):
                Text(content.textToDisplay)
                if let imageUrl = content.imageUrl {
                    Image(uiImage: image)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width:100, height:100)
                        .onReceive(ImageLoader(urlString: imageUrl).didChange) { data in
                            self.image = UIImage(data: data) ?? UIImage()
                        }
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
