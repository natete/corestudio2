import { InputInfo } from '../../shared-components/input/input-info';

export const ClientFormDefinition: InputInfo[] = [
  {
    type: 'toggle',
    formControlName: 'isActive',
    placeholder: 'Activo',
    errors: []
  },
  {
    type: 'text',
    formControlName: 'name',
    placeholder: 'Nombre',
    errors: [
      { type: 'required', when: ['touched'], message: 'El nombre es necesario' }
    ]
  },
  {
    type: 'text',
    formControlName: 'firstSurname',
    placeholder: 'Primer apellido',
    errors: [
      { type: 'required', when: ['touched'], message: 'El primer apellido es necesario' }
    ]
  },
  {
    type: 'text',
    formControlName: 'secondSurname',
    placeholder: 'Segundo apellido',
    errors: []
  },
  {
    type: 'text',
    formControlName: 'address',
    placeholder: 'Dirección',
    errors: []
  },
  {
    type: 'text',
    formControlName: 'firstPhone',
    placeholder: 'Teléfono 1',
    suffix: 'fa-phone',
    errors: [
      { type: 'pattern', val: /^[0-9]{9}$/, when: ['touched'], message: 'El teléfono debe tener 9 cifras sin espacios' }
    ]
  },
  {
    type: 'number',
    formControlName: 'secondPhone',
    placeholder: 'Teléfono 2',
    suffix: 'fa-phone',
    errors: [
      { type: 'pattern', val: /^[0-9]{9}$/, when: ['touched'], message: 'El teléfono debe tener 9 cifras sin espacios' }
    ]
  },
  {
    type: 'mail',
    formControlName: 'email',
    placeholder: 'Correo electrónico',
    suffix: 'fa-at',
    errors: [
      { type: 'email', when: ['touched'], message: 'El correo electrónico no tiene un formato válido' }
    ]
  },
  {
    type: 'date',
    formControlName: 'birthdate',
    placeholder: 'Fecha de nacimiento',
    suffix: 'fa-calendar',
    errors: [
      { type: 'required', when: ['touched'], message: 'La fecha de nacimiento es necesaria' }
    ]
  },
  {
    type: 'date',
    formControlName: 'admissionDate',
    placeholder: 'Fecha de alta',
    suffix: 'fa-calendar',
    errors: [
      { type: 'required', when: ['touched'], message: 'La fecha de alta es necesaria' }
    ]
  },
  {
    type: 'textarea',
    formControlName: 'comments',
    placeholder: 'Comentarios técnicos',
    errors: []
  }
];
