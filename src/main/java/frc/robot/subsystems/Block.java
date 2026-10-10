package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Block extends SubsystemBase {

    // Mechanism: Falcon 500 → 5:1 planetary → 15-tooth sprocket → 24-hole rack tube
    // Position setpoints are in motor rotations (output rotations × 5)
    public static TalonFX block = new TalonFX(1, "rio");

    final MotionMagicVoltage m_request = new MotionMagicVoltage(0).withSlot(0);

    private double lastKP       = 3.0;
    private double lastKI       = 0.0;
    private double lastKD       = 0.0;
    private double lastKV       = 0.113;
    private double lastVelocity = 100.0;
    private double lastAccel    = 150.0;

    public Block() {
        TalonFXConfiguration cfg = new TalonFXConfiguration();

        cfg.MotionMagic.MotionMagicCruiseVelocity = lastVelocity;
        cfg.MotionMagic.MotionMagicAcceleration   = lastAccel;
        cfg.MotionMagic.MotionMagicJerk           = 500.0;

        cfg.Slot0.kP = lastKP;
        cfg.Slot0.kI = lastKI;
        cfg.Slot0.kD = lastKD;
        cfg.Slot0.kV = lastKV;

        block.getConfigurator().apply(cfg);
        block.setPosition(0);

        SmartDashboard.putNumber("Block kP",       lastKP);
        SmartDashboard.putNumber("Block kI",       lastKI);
        SmartDashboard.putNumber("Block kD",       lastKD);
        SmartDashboard.putNumber("Block kV",       lastKV);
        SmartDashboard.putNumber("Block Velocity", lastVelocity);
        SmartDashboard.putNumber("Block Accel",    lastAccel);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Block Motor Position", block.getPosition().getValueAsDouble());

        double kP       = SmartDashboard.getNumber("Block kP",       lastKP);
        double kI       = SmartDashboard.getNumber("Block kI",       lastKI);
        double kD       = SmartDashboard.getNumber("Block kD",       lastKD);
        double kV       = SmartDashboard.getNumber("Block kV",       lastKV);
        double velocity = SmartDashboard.getNumber("Block Velocity", lastVelocity);
        double accel    = SmartDashboard.getNumber("Block Accel",    lastAccel);

        if (kP != lastKP || kI != lastKI || kD != lastKD || kV != lastKV) {
            Slot0Configs gains = new Slot0Configs();
            gains.kP = kP;  gains.kI = kI;  gains.kD = kD;  gains.kV = kV;
            block.getConfigurator().apply(gains);
            lastKP = kP;  lastKI = kI;  lastKD = kD;  lastKV = kV;
        }

        if (velocity != lastVelocity || accel != lastAccel) {
            MotionMagicConfigs mm = new MotionMagicConfigs();
            mm.MotionMagicCruiseVelocity = velocity;
            mm.MotionMagicAcceleration   = accel;
            mm.MotionMagicJerk           = 500.0;
            block.getConfigurator().apply(mm);
            lastVelocity = velocity;
            lastAccel    = accel;
        }
    }

    public void blockUp() {
        block.setControl(m_request.withPosition(7.34));
    }

    public void blockDown() {
        block.setControl(m_request.withPosition(0.0));
    }

    public Command Zero() {
        return Commands.runOnce(() -> block.setPosition(0));
    }

    public Command Up() {
        return Commands.runOnce(() -> blockUp());
    }

    public Command Down() {
        return Commands.runOnce(() -> blockDown());
    }
}
