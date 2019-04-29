package by.it.mazniou.pattern.chain_of_responsibility;


import by.it.mazniou.pattern.chain_of_responsibility.space.SpaceShip;
import by.it.mazniou.pattern.chain_of_responsibility.space.crew.AbstractCrewMember;

/*
Chain of Responsibility
*/
public class Solution {
    public static void main(String[] args) {
        SpaceShip spaceShip = new SpaceShip();
        AbstractCrewMember crewMember = spaceShip.getCrewChain();

        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.EXPERT, "ATTACK");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.NOVICE, "WASH THE FLOOR");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.INTERMEDIATE, "CHECK ENGINE");
        System.out.println("-----------------------------------------");
        crewMember.handleRequest(AbstractCrewMember.CompetencyLevel.ADVANCED, "SET ROUTE");
    }
}
