package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Kicker extends SubsystemBase {

public static TalonFX m_kickerFX = new TalonFX(2, "Rio");


public void Feed(){
    m_kickerFX.set(0.3);
    SmartDashboard.putBoolean("Command-Fast", true);
}

public void Reverse(){
    m_kickerFX.set(-0.3);
    SmartDashboard.putBoolean("Command-Reverse", true);
}

public void Off(){
    m_kickerFX.set(0);
    SmartDashboard.putBoolean("Command-Stop", true);
}

public void feedAndShoot() {
    m_kickerFX.set(.5);
}


public Command FEED(){
    return Commands.runOnce(
        () -> {
            Feed();
        }
         );
}

public Command REVERSE(){
    return Commands.runOnce(
        () -> {
            Reverse();
        }
    );
}

public Command KICKEROFF(){
    return Commands.runOnce(
        () -> {
            Off();
        }
    );
}

}
