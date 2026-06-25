package command;

// ---------- Command interface ----------
interface Command {
    void execute();
    void undo(); // bonus: support undo
}

// ---------- Receiver ----------
class Light {
    private final String location;
    public Light(String location) { this.location = location; }

    public void on()  { System.out.println(location + " light is ON."); }
    public void off() { System.out.println(location + " light is OFF."); }
}

// ---------- Concrete commands ----------
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }

    public void execute() { light.on(); }
    public void undo()    { light.off(); }
}

class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light) { this.light = light; }

    public void execute() { light.off(); }
    public void undo()    { light.on(); }
}

// ---------- Invoker ----------
class RemoteControl {
    private Command lastCommand;

    public void pressButton(Command command) {
        command.execute();
        lastCommand = command;
    }

    public void pressUndo() {
        if (lastCommand != null) {
            System.out.print("Undoing last command → ");
            lastCommand.undo();
        }
    }
}

// ---------- Test ----------
class CommandTest {
    public static void main(String[] args) {
        Light livingRoom = new Light("Living Room");
        Light bedroom    = new Light("Bedroom");

        Command livingOn  = new LightOnCommand(livingRoom);
        Command livingOff = new LightOffCommand(livingRoom);
        Command bedOn     = new LightOnCommand(bedroom);

        RemoteControl remote = new RemoteControl();

        remote.pressButton(livingOn);
        remote.pressButton(bedOn);
        remote.pressButton(livingOff);

        System.out.println();
        remote.pressUndo(); // undo lights off → turns living room back on
    }
}
