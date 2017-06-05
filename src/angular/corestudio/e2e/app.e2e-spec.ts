import { CorestudioPage } from './app.po';

describe('corestudio App', () => {
  let page: CorestudioPage;

  beforeEach(() => {
    page = new CorestudioPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
