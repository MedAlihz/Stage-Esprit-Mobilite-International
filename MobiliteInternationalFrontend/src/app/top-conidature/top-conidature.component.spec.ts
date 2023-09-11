import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopConidatureComponent } from './top-conidature.component';

describe('TopConidatureComponent', () => {
  let component: TopConidatureComponent;
  let fixture: ComponentFixture<TopConidatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopConidatureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopConidatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
