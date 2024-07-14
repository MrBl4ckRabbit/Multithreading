package _01._19;

import _01._19.common.*;

public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes type) {
        switch (type) {
            case BMP -> {
                return new BmpReader();
            }
            case JPG -> {
                return new JpgReader();
            }
            case PNG -> {
                return new PngReader();
            }
            default -> throw new IllegalArgumentException("Wrong type");
        }
    }
}
