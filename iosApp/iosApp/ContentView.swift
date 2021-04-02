import SwiftUI
import shared

struct ContentView: View {
    @State var textToDisplay = "loading..."
    
    var body: some View {
        Group{
            Text(textToDisplay)
        }.onAppear {
            let databaseDriverFactory = DatabaseDriverFactory()
            let database = DatabaseModule().createDataBase(driver: databaseDriverFactory.createDriver())
            let databaseHelper = PetsDataBaseHelper(database: database)
            
            let useCase = GetPetsUseCase(repository: PetsRepository(cacheSource: databaseHelper, networkSource: PetsApiClient()))
            
            useCase.execute { (flow: CFlow<NSArray>?, _) in
                flow?.watch(block: { (pets: NSArray?) in
                    self.textToDisplay = (pets![0] as! Pet).name
                })
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
