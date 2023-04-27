import java.util.Scanner;
import java.io.IOException;


public class fedtool
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\n~> FED-TOOL DEV VER. <~\n");
        System.out.print("\nType one of the following commands:\n\n");

        System.out.print("cupdates (Check for updates)\n");
        System.out.print("iupdates (Install the available updates)\n");
        System.out.print("dsearch (Search for an available dnf package)\n");
        System.out.print("fclone (Clone a git repository) - Requires [git] \n");
        System.out.print("nfold (Create a folder)\n");
        System.out.print("nfile (Create a file)\n");
        System.out.print("pkill (End a running process)\n");
        System.out.print("mysensors (Get the current system temperature) - Requires [lm_sensors]\n");
        System.out.print("nvidia-set (Open the nvidia settings)\n");

        System.out.print("\n--> ");

        String command = input.next();
        String response = String.format("Could not find anything for %s", command);
        //cupdates command
        if(command.equals("cupdates"))
        {
            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("dnf check-update");
            } catch (IOException e) {
                System.out.println(e);
                }
            System.out.println(runner.getOutput());
        }//iupdates command
        else if(command.equals("iupdates"))
        {
            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("sudo dnf update");
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//fclone command
        else if(command.equals("fclone"))
        {
            System.out.print("\nDesired repo path: ");
            String repo_path = input.next();
            System.out.print("\nName of the new repo folder: ");
            String repo_folder_name = input.next();
            System.out.print("\nGit repo URL: ");
            String repo_url = input.next();

            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("git clone " + repo_url + " ../../" + repo_path + "/" + repo_folder_name);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//nfold command
        else if(command.equals("nfold"))
        {
            System.out.print("\nFolder name: ");
            String folder_name = input.next();
            System.out.print("\nFolder path: ");
            String folder_path = input.next();

            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("mkdir -v ../../ " + folder_path + "/" + folder_name);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//nfile command
        else if(command.equals("nfile"))
        {
            System.out.print("\nFile name: ");
            String file_name = input.next();
            System.out.print("\nFile extension (without dot): ");
            String file_ext = input.next();
            System.out.print("\nFile path: ");
            String file_path = input.next();

            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("touch ../../ " + file_path + "/" + file_name + "." + file_ext);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//pkill command
        else if(command.equals("pkill"))
        {
            System.out.print("\nProcess name: ");
            String process_name = input.next();

            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("kill " + process_name);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//mysensors command
        else if(command.equals("mysensors"))
        {
            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("sensors");
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//nvidia-set command
        else if(command.equals("nvidia-set"))
        {
            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("nvidia-settings");
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }//dsearch command
        else if(command.equals("dsearch"))
        {
            System.out.print("\nPackage name: ");
            String package_name = input.next();

            SystemExecutor runner = new SystemExecutor(SystemExecutor.OutputCollection.ERRORS);

            try {
                runner.execute("dnf search " + package_name);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(runner.getOutput());
        }
        else
        {
            System.out.println("\n" + response + "\n");
        }
    }
}