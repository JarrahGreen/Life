package org.example.objects;

public class Collider {
    public int width;
    public int height;
    public int x;
    public int y;

    public Collider(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public Collision checkCollision(int targetX, int targetY, double targetXVel, double targetYVel, int targetWidth, int targetHeight) {

        int centreX = x + width / 2;
        int centreY = y + height / 2;
        int centreTargetX = targetX + targetWidth / 2;
        int centreTargetY = targetY + targetHeight / 2;

        var angle = switch ((centreTargetX > centreX ? 1 : 0) + (centreTargetY > centreY ? 2 : 0)) {
            case 0 -> 1 * Math.PI / 4;
            case 1 -> 3 * Math.PI / 4;
            case 2 -> -1 * Math.PI / 4;
            case 3 -> -3 * Math.PI / 4;
            default ->
                    throw new IllegalStateException("Unexpected value: " + (centreTargetX > centreX ? 1 : 0) + (centreTargetY > centreY ? 2 : 0));
        };

        int targetCornerX = centreTargetX > centreX ? x + width : x;
        int targetCornerY = centreTargetY > centreY ? y + height : y;
        System.out.printf("%d, %d%n", targetCornerX, targetCornerY);

        double targetAngle = Math.atan2(targetYVel, targetXVel);
        if (Math.abs(targetYVel) < 0.001 && Math.abs(targetXVel) < 0.001) {
            targetAngle = angle;
        }


        double differenceAngle = Math.atan2(centreTargetY - targetCornerY, centreTargetX - targetCornerX);

        System.out.printf("%f | %f | %f%n", Math.toDegrees(angle), Math.toDegrees(targetAngle), Math.toDegrees(differenceAngle));
        if (targetX + targetXVel + targetWidth > x && targetX + targetXVel < x + width
                && targetY + targetYVel + targetHeight > y && targetY + targetYVel < y + height) {
            if (angle == Math.PI / 4) {
                return targetAngle < differenceAngle ? Collision.LEFT : Collision.FLOOR;
            } else if (angle == 3 * Math.PI / 4) {
                return targetAngle < differenceAngle ? Collision.RIGHT : Collision.FLOOR;
            } else if (angle == -1 * Math.PI / 4) {
                return targetAngle < differenceAngle ? Collision.CEILING : Collision.LEFT;
            } else if (angle == -3 * Math.PI / 4) {
                return targetAngle < differenceAngle ? Collision.CEILING : Collision.RIGHT;
            }
        }


        return Collision.NONE;
    }
}
