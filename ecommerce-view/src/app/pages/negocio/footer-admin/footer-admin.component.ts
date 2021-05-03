import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer-admin',
  templateUrl: './footer-admin.component.html',
  styleUrls: ['./footer-admin.component.scss']
})
export class FooterAdminComponent implements OnInit {

  public year = new Date().getFullYear();
  public icons = [
    { name: 'fab fa-facebook', link: 'https://www.facebook.com/samuel.souza.946517', toolTip: "Siga-nos no Facebook" },
    { name: 'fab fa-instagram', link: 'https://www.instagram.com/samukasouzaddg/?hl=pt-br',  toolTip: "Siga-nos no Instagram"},
    { name: 'fab fa-youtube', link: 'https://www.youtube.com/channel/UCFn1GkGhJouxqcEdGfGXoKw',  toolTip: "Se inscreva no nosso canal do YouTube"},
    { name: 'fab fa-linkedin', link: 'https://www.linkedin.com/in/samuel-souza-551a34196/',  toolTip: "Siga-nos no Linkedin"},
    { name: 'fab fa-github', link: 'https://github.com/samuelgsete', toolTip: 'Visite nosso GitHub'}
  ];

  public constructor() { }

  ngOnInit(): void  {}
}