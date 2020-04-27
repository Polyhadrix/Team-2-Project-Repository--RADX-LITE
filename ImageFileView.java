package ris;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/* ImageFileView.java is used by ImagePicker.java. */
public class ImageFileView extends FileView {
	// Define image paths for the icons representing the different filetypes
    ImageIcon jpgIcon = Utils.createImageIcon("images/jpgIcon.png");
    ImageIcon gifIcon = Utils.createImageIcon("images/gifIcon.png");
    ImageIcon tiffIcon = Utils.createImageIcon("images/tiffIcon.png");
    ImageIcon pngIcon = Utils.createImageIcon("images/pngIcon.png");

    // Overrides to let the FileView figure these out
    public String getName(File f) {return null;}
    public String getDescription(File f) {return null;}
    public Boolean isTraversable(File f) {return null;}

    // Add a tool-tip/description to the filetypes
    public String getTypeDescription(File f) {
        String extension = Utils.getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) { type = "JPEG Image";}
            else if (extension.equals(Utils.gif)){ type = "GIF Image"; }
            else if (extension.equals(Utils.tiff) || extension.equals(Utils.tif)) { type = "TIFF Image"; }
            else if (extension.equals(Utils.png)){ type = "PNG Image"; }
        }
        return type;
    }

    // Assign the appropriate icons to the files
    public Icon getIcon(File f) {
        String extension = Utils.getExtension(f);
        Icon icon = null;

        if (extension != null) {
            if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) icon = jpgIcon;
            else if (extension.equals(Utils.gif)) icon = gifIcon;
            else if (extension.equals(Utils.tiff) || extension.equals(Utils.tif)) icon = tiffIcon;
            else if (extension.equals(Utils.png)) icon = pngIcon;
        }
        return icon;
    }
}
