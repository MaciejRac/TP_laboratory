package com.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        System.out.println("Number of Command Line Argument = "+args.length);
		for(int i = 0; i< args.length; i++) 
			System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
        if(args.length==0)
            System.out.println("brak argumentow");
        
        int argument = Integer.parseInt(args[0]);
        Set<Mage> set;
        switch (argument) {
            case 0: //unsorted set
                set = new HashSet<>();
                break;
            case 1:
                set= new TreeSet<>();
                break;
            case 2:
                set = new TreeSet<>(new AlternativeComparator());
                break;
            default:
                System.out.println("Nieznany parametr. Dostępne opcje: none, natural, alternative.");
                return;
        }
       
        Mage merlin = new Mage(10, "Merlin", 100.0);
        Mage oper = new Mage(7, "OPer", 150.0);
        Mage jan = new Mage(15, "JAN", 120.0);
        Mage pppp = new Mage(5, "PPPP", 110.0);
        Mage morgana1 = new Mage(3, "Morgana", 130.0);
        Mage morgana2 = new Mage(5, "Morgana", 975);
        Mage fsetgss = new Mage(10, "FsetGSS",320.0);
        Mage lova = new Mage(90, "LOva", 91.1);
        Mage xxxz = new Mage(32, "XXXZ", 84.6);
        Mage merlin2=new Mage(68, "Merlin", 100.0);

        merlin.addApprentice(pppp);
        merlin.addApprentice(morgana1);
        oper.addApprentice(fsetgss);
        lova.addApprentice(xxxz);
        morgana1.addApprentice(morgana2);

        set.add(merlin);
        set.add(oper);
        set.add(jan);
        set.add(pppp);
        set.add(morgana1);
        set.add(morgana2);
        set.add(fsetgss);
        set.add(lova);
        set.add(xxxz);
        set.add(merlin2);

        for (Mage mage : set) {
            System.out.println(mage.hashCode());
        }
        Mage.printSet(set,"-");
        //Mage al= new Mage(1,"ada",2.1);
        boolean boolValue = argument != 0;
        Map<Mage, Integer> descendantStats = Mage.generateDescendantStatistics(set,boolValue);
        Mage.printDescendantStatistics(descendantStats);
        System.out.println(set.toString());
    }
}
