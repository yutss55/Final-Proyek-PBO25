package final_proyek_pbo.util;

import javafx.scene.Scene;

public class NavigationManager {
    private static Scene scene;
    public static void setScene (Scene scene){
        NavigationManager.scene = scene;
    }
    public static void setRoot(javafx.scene.Parent root){
        scene.setRoot(root);
    }
    public static Scene getScene(){
    return scene;
}
}
