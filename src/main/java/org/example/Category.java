package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Category
{


    int numberOfLine;
    public int getNumberOfLine() {
        return numberOfLine;
    }
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String PURPLE_BOLD = "\u001B[1m\u001B[35m";
    static Logger logger = Logger.getLogger(Category.class.getName());
    private static final String CATEGORY_DATA_FILE_PATH = "src/main/resources/Data/categoryData.txt";
    private static final String DATA_DIRECTORY_PATH = "src/main/resources/Data/";
    public boolean isAddNewCategoryFlag() {
        return addNewCategoryFlag;
    }

    public void setAddNewCategoryFlag(boolean addNewCategoryFlag) {
        this.addNewCategoryFlag = addNewCategoryFlag;
    }

    private boolean addNewCategoryFlag ;

    public boolean isDeleteCategoryFlag() {
        return deleteCategoryFlag;
    }

    public void setDeleteCategoryFlag(boolean deleteCategoryFlag) {
        this.deleteCategoryFlag = deleteCategoryFlag;
    }

    public boolean isUpdateCategoryFlag() {
        return updateCategoryFlag;
    }

    public void setUpdateCategoryFlag(boolean updateCategoryFlag) {
        this.updateCategoryFlag = updateCategoryFlag;
    }

    private boolean deleteCategoryFlag ;
    private boolean updateCategoryFlag ;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    private String categoryName;
    public Category() {
        addNewCategoryFlag = false;
        deleteCategoryFlag=false;
        updateCategoryFlag=false;
    }

    public void addNewCategory (String name){

        try (RandomAccessFile ref = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {

                if (s.equals(name)) {
                    setAddNewCategoryFlag(false);
                    logger.log(Level.WARNING,"This category already exists");
                    //throw new CategoryAlreadyExistsException("Category already exists: " + name);
                    return;
                }
            }
            setAddNewCategoryFlag(true);
        } catch (IOException e) {
            throw new RuntimeException("Error while checking for existing category", e);
        }
    }


    public void addThisCategory(String name) {
        try (RandomAccessFile file = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw")) {
            file.seek(file.length());
            file.writeBytes(name + "\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }

        try (RandomAccessFile file = new RandomAccessFile(DATA_DIRECTORY_PATH + name + ".txt", "rw")) {
            file.seek(file.length());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }

    public void deleteTheCategory(String categoryName){
        try (
                RandomAccessFile ref = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw")) {
            String s;
            numberOfLine = -1;
            while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine +1;
                if (s.equals(categoryName)) {
                    setDeleteCategoryFlag(true);
                    return;
                }
            }
            setAddNewCategoryFlag(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteThisCategory (String name){
        RandomAccessFile raf=null;
        try {
            String ff =DATA_DIRECTORY_PATH+name+".txt";
            Path filePath = Paths.get(ff);
            Files.delete(filePath);
            raf = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw");
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < getNumberOfLine()) {
                start = currentPos;
                raf.readLine();
                currentPos = raf.getFilePointer();
                currentLine++;
            }

            // Save the rest of the file after the line to be deleted
            long end = raf.length();
            byte[] remainingBytes = new byte[(int) (end - currentPos)];
            raf.read(remainingBytes);

            raf.seek(start);
            raf.write(remainingBytes);
            raf.setLength(start + remainingBytes.length);
            raf.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuCategory() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[34m----------------------------------
                |                                 |
                |     1.View all categories       |
                |     2.add new category          |
                |     3.edit category             |
                |     4.delete category           |
                |                                 |
                ----------------------------------
                """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[37m");
        choice = scanner.nextInt();
        if (choice == 1) {
            printAllCategory();
            back();
        }
        else if (choice == 2) {
            logger.log(Level.INFO,"Enter The name of category");
            String names = getTheNameOfCat(scanner);
            addNewCategory(names);
            extractedAddNewCat(names);
            back();
        }
        else if (choice == 3) {
            logger.log(Level.INFO,"Enter The name of category that you will modify");
            String names = getTheNameOfCat(scanner);
            logger.log(Level.INFO,"Enter The new name of category");
            String newName = getTheNameOfCat(scanner);
            editTheName(names);
            extractedEditCat(names,newName);
            back();
        }
        else if (choice == 4) {
            logger.log(Level.INFO,"Enter The name of category");
            String names = getTheNameOfCat(scanner);
            deleteTheCategory(names);
            extractedDeleteCat(names);
            back();
        }

        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+RESET_COLOR);
            menuCategory();
        }

    }

    public void extractedDeleteCat(String names) {
        if(isDeleteCategoryFlag()) {
            deleteThisCategory(names);
            logger.log(Level.INFO,PURPLE_BOLD+"The category deletion was completed successfully"+RESET_COLOR);
        }

        else
            logger.log(Level.WARNING,"This category is not exist to delete!");
    }

    public static String getTheNameOfCat(Scanner scanner) {
        return scanner.next();
    }

    public void extractedAddNewCat(String names) {
        if (isAddNewCategoryFlag()){
            addThisCategory(names);
            logger.log(Level.INFO, PURPLE_BOLD+"The addition was completed successfully"+RESET_COLOR);
        }
        else
            logger.log(Level.WARNING,"This category is already exist!");
    }
    public void back(){
        logger.log(Level.INFO, """

                1) back\s
                2) Exit""");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        if(choice==1)
            menuCategory();
        System.exit(0);
    }


    public void printAllCategory(){
        try (RandomAccessFile ref = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw")) {
            String s;
            while ((s = ref.readLine()) != null)
            {
                logger.log(Level.INFO,"\u001B[36m"+s+RESET_COLOR);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTheName(String categoryName) {
        numberOfLine = 0-1;
        try (RandomAccessFile ref = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw")) {
            String s;

            while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine +1;
                if (s.equals(categoryName)) {
                    setUpdateCategoryFlag(true);
                    return;
                }
            }
            setUpdateCategoryFlag(false);
            logger.log(Level.INFO,categoryName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void extractedEditCat(String names,String newName) {
        if (isUpdateCategoryFlag()){
            editThisCategory(names,newName);
            logger.log(Level.INFO, PURPLE_BOLD+"The edit was completed successfully"+RESET_COLOR);
        }
        else
            logger.log(Level.WARNING,"This category does not exist!");
    }

    public void editThisCategory(String names, String newName) {
        try {
            String oldPath =DATA_DIRECTORY_PATH+names+".txt";
            String newPath =DATA_DIRECTORY_PATH+newName+".txt";
            Path oldFilePath = Paths.get(oldPath);
            Path newFilePath = Paths.get(newPath);
            Files.move(oldFilePath,newFilePath);
            RandomAccessFile raf = new RandomAccessFile(CATEGORY_DATA_FILE_PATH, "rw");
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < getNumberOfLine()) {
                start = currentPos;
                raf.readLine();
                currentPos = raf.getFilePointer();
                currentLine++;
            }

            // Save the rest of the file after the line to be deleted
            long end = raf.length();
            byte[] remainingBytes = new byte[(int) (end - currentPos)];
            raf.read(remainingBytes);
            raf.seek(start);
            raf.write(remainingBytes);
            raf.setLength(start + remainingBytes.length);
            raf.seek(raf.length());
            raf.writeBytes(newName+"\n");
            raf.close();
            raf.close();

        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
