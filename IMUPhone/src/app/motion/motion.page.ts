import { Component, OnInit } from '@angular/core';
import {DeviceMotion, DeviceMotionAccelerationData} from '@ionic-native/device-motion/ngx';
import {DeviceOrientation, DeviceOrientationCompassHeading} from '@ionic-native/device-orientation/ngx';
import {Socket} from 'ngx-socket-io';

@Component({
  selector: 'app-motion',
  templateUrl: './motion.page.html',
  styleUrls: ['./motion.page.scss'],
})
export class MotionPage implements OnInit {
  accelerationData: DeviceMotionAccelerationData;
  orientationData: DeviceOrientationCompassHeading;

  constructor(
      private deviceMotion: DeviceMotion,
      private deviceOrientation: DeviceOrientation,
      private socket: Socket
  ) { }

  ngOnInit() {
      if (this.socket) {
          this.socket.emit('set-nickname', 'sender');
          this.socket.emit('message', 'test');
      } else {
          console.log('socket is undef');
      }
      const motionSub = this.deviceMotion.watchAcceleration().subscribe(acceleration => {
          this.accelerationData = acceleration;
          this.socket.emit('movement', {x: acceleration.x, y: acceleration.y, z: acceleration.z});
          console.log(acceleration);
      });
      const orientationSub = this.deviceOrientation.watchHeading().subscribe(orientation => {
          this.orientationData = orientation;
          this.socket.emit('direction', orientation.trueHeading);
          console.log(orientation);
      });
  }

}
