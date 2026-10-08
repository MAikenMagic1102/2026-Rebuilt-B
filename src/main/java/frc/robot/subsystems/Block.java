package frc.robot.subsystems;
import java.nio.channels.Pipe;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Block extends SubsystemBase {
    public static TalonFX block = new TalonFX(2,"rio");
    final PositionVoltage m_block = new PositionVoltage(0).withSlot(0);

    @Override
    public void periodic () {
        SmartDashboard.putNumber("Block Motor Position", block.getPosition().getValueAsDouble());
    }
     
    public void blockUp(){
        block.setControl(m_block.withPosition(3));
    }

    public void blockDown(){
        block.setControl(m_block.withPosition(0));
    }

    public Command Up(){
return Commands.runOnce(
    () -> {
        blockUp();
    }
);

}


public Command Down(){
    return Commands.runOnce(
        () -> {
            blockDown();
        }
    );
}

}