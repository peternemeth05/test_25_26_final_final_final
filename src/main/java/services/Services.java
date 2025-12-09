package services;

public abstract class Services {

    protected int duration;
    protected boolean active;
    protected String name;
    protected int CurDuration;

    public Services(String name, int duration, boolean active) {
        this.duration = duration;
        this.active = active;
        this.name = name;
        this.CurDuration = 0;
    }

    public Services getServices(){
        return this;
    }

    public String getName() {
        return name;
    }

    public int getCurDuration() {
        return CurDuration;
    }

    public void increaseCurDuration(){
        if (active){
            this.CurDuration++;
        }

    }

    public int getDuration() {
        return duration;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
