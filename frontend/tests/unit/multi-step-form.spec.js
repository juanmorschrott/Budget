import { expect } from 'chai'
import { mount } from '@vue/test-utils'
import MultiStepForm from '@/components/MultiStepForm.vue'

describe('MultiStepForm.vue', () => {

  it('it has created hook', () => {
    expect(MultiStepForm.created).to.be.a('function');
  })

  it('step decrements', () => {
    const wrapper = mount(MultiStepForm);
    wrapper.step = 2;
    wrapper.trigger('previousTab');
    expect(wrapper.vm.step).to.equal(1);
  })

  it('step does not increment if is not valid', () => {
    const wrapper = mount(MultiStepForm);
    wrapper.step = 1;
    wrapper.trigger('nextTab');
    expect(wrapper.vm.step).to.equal(1);
  })

  it('the modal opens', () => {
    const wrapper = mount(MultiStepForm);
    wrapper.vm.openModal('fake text');
    expect(wrapper.vm.showModal).to.be.true;
  })

})
