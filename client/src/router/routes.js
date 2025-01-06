const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('src/pages/board/BoardListPage.vue') },
      {
        path: '/detail/:id',
        name: 'BoardDetailPage',
        component: () => import('src/pages/board/BoardDetialPage.vue'),
      },
      {
        path: '/write',
        name: 'BoardWritePage',
        component: () => import('src/pages/board/BoardWritePage.vue'),
      },
      {
        path: '/write/:id',
        name: 'BoardEditPage',
        component: () => import('src/pages/board/BoardWritePage.vue'),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
]

export default routes
