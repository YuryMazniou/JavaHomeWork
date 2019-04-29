package by.it.mazniou.pattern.chain_of_responsibility.space.crew;

public abstract class AbstractCrewMember {
    public enum CompetencyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    protected CompetencyLevel competencyLevel;

    protected AbstractCrewMember nextCrewMember;

    public void setNextCrewMember(AbstractCrewMember nextCrewMember) {
        this.nextCrewMember = nextCrewMember;
    }

    public void handleRequest(CompetencyLevel competencyLevel, String request) {
        if(this.competencyLevel==competencyLevel)doTheJob(request);
        else if (nextCrewMember != null) {
            nextCrewMember.handleRequest(competencyLevel, request);
        }
    }

    protected abstract void doTheJob(String request);
}
