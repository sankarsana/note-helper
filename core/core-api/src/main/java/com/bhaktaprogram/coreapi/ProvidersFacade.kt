package com.bhaktaprogram.coreapi

import com.bhaktaprogram.coreapi.navigation.NavigationProvider
import com.bhaktaprogram.coreapi.navigation.RoutersProvider
import com.bhaktaprogram.coreapi.repository.RepositoryProvider

interface ProvidersFacade : RoutersProvider, RepositoryProvider,
    AppContextProvider, NavigationProvider