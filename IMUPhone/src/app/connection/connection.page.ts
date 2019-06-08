import { Component, OnInit } from '@angular/core';
import {Socket} from 'ngx-socket-io';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.page.html',
  styleUrls: ['./connection.page.scss'],
})
export class ConnectionPage implements OnInit {
  x: any;
  y: any;
  z: any;
  alpha: any;
  beta: any;
  gamma: any;

  constructor(
      private socket: Socket
  ) {
    if (this.socket) {
      this.socket.emit('set-nickname', 'sender');
      this.socket.emit('message', 'test');
    } else {
      console.log('socket is undef');
    }
  }

  ngOnInit() {

    window.addEventListener('compassneedscalibration', (event) => {
      event.preventDefault();
    }, true);

    window.addEventListener('devicemotion', ev => {
      this.x = Math.round(ev.acceleration.x);
      this.y = Math.round(ev.acceleration.y);
      this.z = Math.round(ev.acceleration.z);
      this.socket.emit('movement', {x: this.x, y: this.y, z: this.z});
    }, true);

    window.addEventListener('deviceorientation', ev => {
      this.alpha = Math.round(ev.alpha);
      this.beta = Math.round(ev.beta);
      this.gamma = Math.round(ev.gamma);
      this.socket.emit('direction', {alpha: this.alpha, beta: this.beta, gamma: this.gamma});
    }, true);
  }

}
