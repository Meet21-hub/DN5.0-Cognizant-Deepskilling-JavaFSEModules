package mvc;

// ---------- Model ----------
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name  = name;
        this.id    = id;
        this.grade = grade;
    }

    public String getName()  { return name; }
    public String getId()    { return id; }
    public String getGrade() { return grade; }

    public void setName(String name)   { this.name  = name; }
    public void setId(String id)       { this.id    = id; }
    public void setGrade(String grade) { this.grade = grade; }
}

// ---------- View ----------
class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("=== Student Record ===");
        System.out.println("  Name  : " + name);
        System.out.println("  ID    : " + id);
        System.out.println("  Grade : " + grade);
        System.out.println("====================");
    }
}

// ---------- Controller ----------
class StudentController {
    private final Student model;
    private final StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view  = view;
    }

    // Getters (read from model)
    public String getStudentName()  { return model.getName(); }
    public String getStudentId()    { return model.getId(); }
    public String getStudentGrade() { return model.getGrade(); }

    // Setters (update model)
    public void setStudentName(String name)   { model.setName(name); }
    public void setStudentId(String id)       { model.setId(id); }
    public void setStudentGrade(String grade) { model.setGrade(grade); }

    // Render view
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// ---------- Main ----------
class MVCTest {
    public static void main(String[] args) {
        // Create model
        Student student = new Student("Anshul", "CS2021", "A");

        // Create view
        StudentView view = new StudentView();

        // Wire controller
        StudentController controller = new StudentController(student, view);

        // Display initial record
        System.out.println("Initial record:");
        controller.updateView();

        // Update via controller
        controller.setStudentGrade("A+");
        controller.setStudentName("Anshul S.");

        System.out.println("\nAfter update:");
        controller.updateView();
    }
}
