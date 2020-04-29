import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) return true;

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if ( extension.equals(Utils.jpeg) || extension.equals(Utils.jpg) || extension.equals(Utils.gif) || extension.equals(Utils.png)|| extension.equals(Utils.tiff) || extension.equals(Utils.tif) ) return true;
            else return false;
        } return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Images Only";
    }
}
