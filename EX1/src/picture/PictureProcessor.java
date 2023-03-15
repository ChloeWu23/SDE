package picture;

public class PictureProcessor {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("To get help, write PictureProcessor help");
            return;
        } else if (args[0].equals(("help"))) {
            showHelp();
            return;
        } else if (args[0].equals("grayscale")) {
            doGrayScale(args);
        } else if (args[0].equals("invert")) {
            doInvert(args);
        } else if (args[0].equals("flip")) {
            doFlip(args);
        } else if (args[0].equals("rotate")) {
            doRotate(args);
        } else if (args[0].equals("blend")) {
            doBlend(args);
        } else if (args[0].equals("blur")) {
            doBlur(args);
        } else {
            showHelp();
        }

    }

    private static void doGrayScale(String[] args) {
        Picture current_pic = new Picture(args[1]);
        if (current_pic == null) {
            showHelp();
            return;
        }
        for (int i = 0; i < current_pic.getWidth(); i++) {
            for (int j = 0; j < current_pic.getHeight(); j++) {
                Color rgb = current_pic.getPixel(i, j);
                int avg = (rgb.red() + rgb.blue() + rgb.green()) / 3;
                Color new_color = new Color(avg, avg, avg);
                current_pic.setPixel(i, j, new_color);
            }
        }
        current_pic.saveAs(args[2]);
    }

    private static void doInvert(String[] args) {
        Picture current_pic = new Picture(args[1]);
        if (current_pic == null) {
            showHelp();
            return;
        }
        for (int i = 0; i < current_pic.getWidth(); i++) {
            for (int j = 0; j < current_pic.getHeight(); j++) {
                Color rgb = current_pic.getPixel(i, j);
                int num1 = 255 - rgb.red();
                int num2 = 255 - rgb.green();
                int num3 = 255 - rgb.blue();
                Color new_color = new Color(num1, num2, num3);
                current_pic.setPixel(i, j, new_color);
            }
        }
        current_pic.saveAs(args[2]);
    }

    private static void doRotate(String[] args) {
        Picture current_pic = new Picture(args[2]);
        int angle = Integer.parseInt(args[1]);
        //System.out.println(angle);
        if (current_pic == null) {
            showHelp();
            return;
        }
        //System.out.println(angle);
        int new_width, new_height;
        switch (angle) {
            case 0:
                return;

            case 90:
                new_width = current_pic.getHeight();
                new_height = current_pic.getWidth();
                break;

            case 180:
                //case 90:
                new_width = current_pic.getWidth();
                new_height = current_pic.getHeight();
                break;

            case 270:
                //case 180:
                new_width = current_pic.getHeight();
                new_height = current_pic.getWidth();
                break;

            default:
                throw new IllegalArgumentException("Invalid Angle");

        }

        Picture newPic = new Picture(new_width, new_height);
        // 90: x,y -> y , w - 1 - x
        // 180: x,y -> w - 1 - x, h - 1 - y
        // 270: x,y -> h - 1 - y, x
        for (int x = 0; x < new_width; x++) {
            for (int y = 0; y < new_height; y++) {
                switch (angle) {
                    case 90:
                        newPic.setPixel(x, y, current_pic.getPixel(y, new_width - 1 - x));
                        //System.out.println("check");
                        continue;
                    case 180:
                        newPic.setPixel(x, y, current_pic.getPixel(new_width - 1 - x, new_height - 1 - y));
                        continue;
                    case 270:
                        newPic.setPixel(x, y, current_pic.getPixel(new_height - 1 - y, x));
                        continue;

                }
            }
        }
        newPic.saveAs(args[3]);
    }

    private static void doFlip(String[] args) {
        Picture current_pic = new Picture(args[2]);
        if (current_pic == null) {
            showHelp();
            return;
        }
        if (args[1].equals("H")) {
            Picture newPic = new Picture(current_pic.getWidth(), current_pic.getHeight());
            for (int x = 0; x < current_pic.getWidth(); x++) {
                for (int y = 0; y < current_pic.getHeight(); y++) {
                    newPic.setPixel(x, y, current_pic.getPixel(newPic.getWidth() - 1 - x, y));
                }
            }
            newPic.saveAs(args[3]);
        }
        if (args[1].equals("V")) {
            Picture newPic = new Picture(current_pic.getWidth(), current_pic.getHeight());
            for (int x = 0; x < current_pic.getWidth(); x++) {
                for (int y = 0; y < current_pic.getHeight(); y++) {
                    newPic.setPixel(x, y, current_pic.getPixel(x, newPic.getHeight() - 1 - y));
                }
            }
            newPic.saveAs(args[3]);
        } else {
            showHelp();
            return;
        }
    }

    private static void doBlend(String[] args) {
        int length = args.length;
        int pic_num = length - 2;
        Picture[] pictures = new Picture[pic_num];
        pictures[0] = new Picture(args[1]);
        for (int i = 1; i < pic_num; i++) {
            pictures[i] = new Picture(args[1 + i]);
        }

        int croppedWidth = pictures[0].getWidth();
        int croppedHeight = pictures[0].getHeight();

        for (int i = 1; i < pic_num; i++) {
            croppedWidth = Math.min(croppedWidth, pictures[i].getWidth());
            croppedHeight = Math.min(croppedHeight, pictures[i].getHeight());
        }

        Picture basePic = new Picture(croppedWidth, croppedHeight);

        for (int x = 0; x < croppedWidth; x++) {
            for (int y = 0; y < croppedHeight; y++) {
                Color newRGB = pictures[0].getPixel(x, y);
                int red1 = newRGB.red();
                int green1 = newRGB.green();
                int blue1 = newRGB.blue();
                for (int i = 1; i < pic_num; i++) {
                    Color pixelRGB = pictures[i].getPixel(x, y);
                    red1 += pixelRGB.red();
                    green1 += pixelRGB.green();
                    blue1 += pixelRGB.blue();
                }
                Color color = new Color(red1 / pic_num, green1 / pic_num, blue1 / pic_num);
                basePic.setPixel(x, y, color);
            }
        }
        basePic.saveAs(args[length - 1]);

    }

    /*
     *DEbug here: need new picture to save change cant change current pic pixel since other points need original pic data
     */
    private static void doBlur(String[] args) {
        Picture current_pic = new Picture(args[1]);
        Picture new_pic = new Picture(args[1]);
        for (int x = 1; x < current_pic.getWidth() - 1; x++) {
            for (int y = 1; y < current_pic.getHeight() - 1; y++) {
                int red1 = 0;
                int green1 = 0;
                int blue1 = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color c = current_pic.getPixel(i + x, j + y);
                        red1 += c.red();
                        green1 += c.green();
                        blue1 += c.blue();
                    }
                }
                //System.out.println(red1);
                Color new_color = new Color(red1 / 9, green1 / 9, blue1 / 9);
                new_pic.setPixel(x, y, new_color);
            }
        }
        new_pic.saveAs(args[2]);
    }

    private static void showHelp() {
        String HELP_String =
                "*** Picture Processor: *** \n"
                        + "invert INPUT OUTPUT.png              - generates the negative of the image\n"
                        + "grayscale INPUT OUTPUT.png           - generates a monochrome version of the image\n"
                        + "flip [V|H] INPUT OUTPUT.png          - flips the image vertically or horizontally\n"
                        + "rotate [90|180|270] INPUT OUTPUT.png - rotates the image by the specified angle\n"
                        + "blend INPUT... OUTPUT.png            - blends the images together\n"
                        + "mosaic n INPUT... OUTPUT.png         - creates a mosaic with tilesize=nxn\n"
                        + "blur INPUT OUTPUT.png                - blurs the specified image\n"
                        + "\n"
                        + "INPUT can be the URL of an image, or a filesystem location.\n";
        System.out.println(HELP_String);
    }


}
