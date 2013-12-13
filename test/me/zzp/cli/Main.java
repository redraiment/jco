package me.zzp.cli;

public class Main {
    public static void main(String[] args) throws Exception {
        args = "-n Joe d e f -p APS -f --age -p AFW -p DWS -a 5000 --amount 5350 -a 6900 --amount 8500 a b c".split("\\s+");

        Employee e = new Employee();
        args = CommandLineOption.assign(e, args);

        System.out.println(e.getName());
        System.out.println(e.getAge());
        System.out.println(e.isFullTime());
        
        String[] projects = e.getProjects();
        if (projects != null) {
            for (String p : e.getProjects()) {
                System.out.println(p);
            }
        }
        
        Integer[] amounts = e.getAmounts();
        for (int a : amounts) {
            System.out.println(a);
        }
        
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
