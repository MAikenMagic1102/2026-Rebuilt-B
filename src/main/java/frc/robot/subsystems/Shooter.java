package frc.robot.subsystems;
import java.nio.channels.Pipe;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Shooter extends SubsystemBase {

    public static TalonFX m_RShooterFx = new TalonFX(1,"rio");
    public static TalonFX m_LShooterFx = new TalonFX(3,"rio");

    private final Kicker m_Kicker = new Kicker();


    private final Follower m_follower = new Follower(m_RShooterFx.getDeviceID(), MotorAlignmentValue.Opposed);



 public void periodic() {
      m_LShooterFx.setControl(m_follower);
}



public void Feed() {
    m_RShooterFx.set(0.75);
SmartDashboard.putBoolean("Command-Forward", true);

}

public void Reverse() {
    m_RShooterFx.set(-0.5);
    SmartDashboard.putBoolean("Command-Backward", true);
}

public void Off(){
    m_RShooterFx.set(0);
    SmartDashboard.putBoolean("Command-Stop", true);
}


public Command HopperDump() {
    return Commands.runOnce(
        () -> {
            Reverse();
            m_Kicker.feedAndShoot();
        }
    );
}

public Command DRUMCLEAR() {
    return Commands.runOnce(
    () -> {
        Feed();
        m_Kicker.Reverse();
    }
    );
}
    public Command DRUMOFF(){
    return Commands.runOnce(
        () -> {
            Off();
            m_Kicker.Off();
        }
    );
}
public Command Intake(){
    return Commands.runOnce(
        () -> {
            Feed();
            m_Kicker.FEED();
        }
    );
}
}
