package by.it.mazniou.pattern.chain_of_responsibility.space;


import by.it.mazniou.pattern.chain_of_responsibility.space.crew.*;

public class SpaceShip {
    public AbstractCrewMember getCrewChain(){
        AbstractCrewMember cabinBoy = new CabinBoy(AbstractCrewMember.CompetencyLevel.NOVICE);
        AbstractCrewMember engineer = new Engineer(AbstractCrewMember.CompetencyLevel.INTERMEDIATE);
        AbstractCrewMember firstMate = new FirstMate(AbstractCrewMember.CompetencyLevel.ADVANCED);
        AbstractCrewMember captain = new Captain(AbstractCrewMember.CompetencyLevel.EXPERT);

        cabinBoy.setNextCrewMember(engineer);
        engineer.setNextCrewMember(firstMate);
        firstMate.setNextCrewMember(captain);

        return cabinBoy;
    }
}
