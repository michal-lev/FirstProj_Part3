package michal_liora;

public class Committee {
    private String name;
    private Lecturer chair;
    private Lecturer[] members;
    private int memberCount;

    public Committee(String name, Lecturer chair) {
        setName(name);
        setChair(chair);
        setMembers(new Lecturer[1]);
        setMemberCount(0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChair(Lecturer lecturer) {
        this.chair = lecturer;
    }

    public void setMembers(Lecturer[] members) {
        this.members = members;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public Lecturer getChair() {
        return chair;
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void removeMember(Lecturer memberToRemove){
        boolean runOverFlag = false;
        int i = 0;
        for (; i < memberCount - 1; i++) {
            if(members[i].equals(memberToRemove)){
                runOverFlag = true;
            }
            if (runOverFlag){
                members[i] = members[i + 1];
            }
        }
        members[i] = null;
        setMemberCount(memberCount - 1);
    }

    public String membersNamesToString() {
        String membersStr = "[";
        int i = 0;
        for (; i < (memberCount - 1); i++){
            membersStr += members[i].getName() + ", " ;
        }
        if(memberCount != 0) {
            membersStr += members[i].getName();
        }
        membersStr += "]";
        return membersStr;
    }

}
